package gwu.hadoop.parquet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.GroupFactory;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.GroupReadSupport;
import org.apache.parquet.hadoop.example.GroupWriteSupport;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;

import java.io.*;

/**
 * Created by wgtmac on 9/19/16.
 */
public class ParquetTest {
    /**
     * Read parquet schema from file, and parse it to MessageType
     * */
    public static MessageType parseSchema() throws IOException {
        InputStream in = ParquetTest.class.getResourceAsStream(
                "/parquet/pair_parquet_schema");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        MessageType schema = MessageTypeParser.parseMessageType(sb.toString());
        System.out.println(schema);
        return schema;
    }

    /**
     * Generate record as Group
     * */
    public static Group create(MessageType schema) {
        // create a parquet record
        GroupFactory groupFactory = new SimpleGroupFactory(schema);
        Group group = groupFactory.newGroup()
                .append("left", "L")
                .append("right", "R");

        System.out.println(group);

        return group;
    }

    /**
     * Write to a parquet file
     * */
    public static void write(MessageType schema, Group data) throws IOException {
        // prepare file config
        Configuration conf = new Configuration();
        Path path = new Path("/tmp/data.parquet");
        FileSystem fs = FileSystem.getLocal(conf);
        if (fs.exists(path))
            fs.delete(path, false);

        // write
        GroupWriteSupport writeSupport = new GroupWriteSupport();
        GroupWriteSupport.setSchema(schema, conf);
        ParquetWriter<Group> writer = new ParquetWriter<>(path, writeSupport,
                ParquetWriter.DEFAULT_COMPRESSION_CODEC_NAME,
                ParquetWriter.DEFAULT_BLOCK_SIZE,
                ParquetWriter.DEFAULT_PAGE_SIZE,
                ParquetWriter.DEFAULT_PAGE_SIZE, /* dictionary page size */
                ParquetWriter.DEFAULT_IS_DICTIONARY_ENABLED,
                ParquetWriter.DEFAULT_IS_VALIDATING_ENABLED,
                ParquetProperties.WriterVersion.PARQUET_1_0, conf);
        writer.write(data);
        writer.close();
    }

    public static void read() throws IOException {
        Path path = new Path("/tmp/data.parquet");

        GroupReadSupport readSupport = new GroupReadSupport();
        ParquetReader<Group> reader = new ParquetReader<>(path, readSupport);

        Group result = reader.read();
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        MessageType schema = parseSchema();

        Group group = create(schema);

        write(schema, group);

        read();
    }
}
