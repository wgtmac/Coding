package gwu.hadoop.avro;

import gwu.hadoop.avro.generated.NewStringPair;
import gwu.hadoop.avro.generated.StringPair;
import gwu.hadoop.avro.generated.User;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by wgtmac on 9/18/16.
 */
public class AvroTest {
    /**
     * Avro schema file
     * */
    // Generic API
    public static void genericAPI() throws IOException {
        // Read schema from a file
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(
                AvroTest.class.getResourceAsStream("/avro/StringPair.avsc"));

        // Create an instance of Avro record
        GenericRecord datum = new GenericData.Record(schema);
        datum.put("left", "L");
        datum.put("right", "R");

        // Serialize to an output stream using [[GenericDatumWriter]]
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null); // no reuse of encoder
        writer.write(datum, encoder);
        encoder.flush();
        out.close();

        // Read the serialized output stream using [[GenericDatumReader]]
        DatumReader<GenericRecord> reader = new GenericDatumReader<>(schema);
        Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null); // no reuse of decoder
        GenericRecord result = reader.read(null, decoder); // no reuse of record
        System.out.println(result);
        assert result.get("left").toString().equals("L");
        assert result.get("right").toString().equals("R");
    }

    // Specific API
    public static void specificAPI() throws IOException {
        // Create a record using code generation
        StringPair datum = new StringPair();
        datum.setLeft("L");
        datum.setRight("R");

        // Serialize the record using [[SpecificDatumWriter]]
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DatumWriter<StringPair> writer = new SpecificDatumWriter<>(StringPair.class);
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        writer.write(datum, encoder);
        encoder.flush();
        out.close();

        // Read record back using [[SpecificDatumReader]]
        DatumReader<StringPair> reader = new SpecificDatumReader<>(StringPair.class);
        Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);
        StringPair result = reader.read(null, decoder);
        System.out.println(result);
    }

    /**
     * Avro object container file
     *
     * Datafile has a header containing metadata, including the Avro schema and
     * a sync marker, followed by a series of (optionally compressed) blocks
     * containing the serialized Avro objects
     * */
    public static void datafile() throws IOException {
        // Create avro records
        User user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);
        User user2 = new User("Ben", 7, "red");
        User user3 = User.newBuilder()
                .setName("Charlie")
                .setFavoriteColor("blue")
                .setFavoriteNumber(null)
                .build();

        File file = new File("/tmp/users.avro");

        // Serialize user1, user2 and user3 to disk
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<>(userDatumWriter);
        dataFileWriter.create(user1.getSchema(), new File("/tmp/users.avro"));
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();

        // Deserialize Users from disk
        DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<>(file, userDatumReader);
        User user = null;
        while (dataFileReader.hasNext()) {
            // Reuse user object by passing it to next(). This saves us from
            // allocating and garbage collecting many objects for files with
            // many items.
            user = dataFileReader.next(user);
            System.out.println(user);
        }
    }

    /**
     * Schema evolution
     * */
    public static void schemaResolution() throws IOException {
        /**
         * old write schema, new read schema
         */
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DatumWriter<StringPair> writer = new SpecificDatumWriter<>(StringPair.class);
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        writer.write(new StringPair("L", "R"), encoder);
        encoder.flush();
        out.close();

        // Read record back using [[SpecificDatumReader]]
        DatumReader<NewStringPair> reader = new SpecificDatumReader<>(
                StringPair.getClassSchema(), NewStringPair.getClassSchema());
        Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);
        NewStringPair result = reader.read(null, decoder);
        System.out.println(result);

        /**
         * new write schema, old read schema (not supported)
         */
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(
                AvroTest.class.getResourceAsStream("/avro/StringPair.avsc"));
        Schema newSchema = parser.parse(
                AvroTest.class.getResourceAsStream("/avro/schema_evolution.avsc"));

        GenericRecord datum = new GenericData.Record(newSchema);
        datum.put("left", "L");
        datum.put("right", "R");
        datum.put("description1", "text");

        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        DatumWriter<GenericRecord> writer2 = new GenericDatumWriter<>(newSchema);
        Encoder encoder2 = EncoderFactory.get().binaryEncoder(out2, null);
        writer2.write(datum, encoder2);
        encoder.flush();
        out.close();

        DatumReader<GenericRecord> reader2 = new GenericDatumReader<>(newSchema, schema);
        Decoder decoder2 = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);
        GenericRecord result2 = reader2.read(null, decoder2);
        System.out.println(result2);
    }

    public static void main(String[] args) throws IOException {
        genericAPI();

        specificAPI();

        datafile();

        schemaResolution();
    }
}
