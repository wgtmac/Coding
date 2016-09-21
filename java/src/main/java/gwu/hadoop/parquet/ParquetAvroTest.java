package gwu.hadoop.parquet;

import gwu.hadoop.avro.generated.StringPair;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.avro.AvroReadSupport;


import java.io.IOException;

/**
 * Created by wgtmac on 9/20/16.
 *
 * Parquet supports Avro, Thrift, ProtoBuf to define its schema
 * This example demonstrates how to use Avro.
 */
public class ParquetAvroTest {
    public static void main(String[] args) throws IOException {
        StringPair datum = new StringPair();
        datum.setLeft("L");
        datum.setRight("R");

        Configuration conf = new Configuration();
        Path path = new Path("/tmp/data.parquet");
        FileSystem fs = FileSystem.getLocal(conf);
        if (fs.exists(path))
            fs.delete(path, false);

        AvroParquetWriter<StringPair> writer =
                new AvroParquetWriter<>(path, StringPair.getClassSchema());
        writer.write(datum);
        writer.close();

        AvroParquetReader<GenericRecord> reader =
                new AvroParquetReader<>(path);
        GenericRecord result = reader.read();
        System.out.println(result);

        Schema.Parser parser = new Schema.Parser();
        Schema projectionSchema = parser.parse(
                ParquetAvroTest.class.getResourceAsStream("/parquet/ProjectedStringPair.avsc"));
        AvroReadSupport.setRequestedProjection(conf, projectionSchema);
        //AvroReadSupport.setAvroReadSchema(conf, projectionSchema);
        reader = new AvroParquetReader<>(conf, path);
        result = reader.read();
        System.out.println(result);
    }
}
