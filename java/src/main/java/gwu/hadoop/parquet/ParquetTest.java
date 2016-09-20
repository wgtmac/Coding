package gwu.hadoop.parquet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.GroupFactory;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.GroupWriteSupport;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;

import java.io.IOException;

/**
 * Created by wgtmac on 9/19/16.
 */
public class ParquetTest {

    public static void test1() throws IOException {
        // define schema
        MessageType schema = MessageTypeParser.parseMessageType(
                "message Pair {\n" +
                        "  required binary left (UTF8);\n" +
                        "  required binary right (UTF8);\n" +
                        "}");

        // create a parquet record
        GroupFactory groupFactory = new SimpleGroupFactory(schema);
        Group group = groupFactory.newGroup()
                .append("left", "L")
                .append("right", "R");

        // create a parquet file
        Configuration conf = new Configuration();
        Path path = new Path("/tmp/data.parquet");
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
        writer.write(group);
        writer.close();
    }


    public static void main(String[] args) throws IOException {
        test1();
    }
}
