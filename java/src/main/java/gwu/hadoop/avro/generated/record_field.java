/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package gwu.hadoop.avro.generated;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class record_field extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"record_field\",\"namespace\":\"gwu.hadoop.avro.generated\",\"fields\":[{\"name\":\"year\",\"type\":\"int\"},{\"name\":\"temperature\",\"type\":\"int\"},{\"name\":\"stationId\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public int year;
  @Deprecated public int temperature;
  @Deprecated public java.lang.CharSequence stationId;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public record_field() {}

  /**
   * All-args constructor.
   */
  public record_field(java.lang.Integer year, java.lang.Integer temperature, java.lang.CharSequence stationId) {
    this.year = year;
    this.temperature = temperature;
    this.stationId = stationId;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return year;
    case 1: return temperature;
    case 2: return stationId;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: year = (java.lang.Integer)value$; break;
    case 1: temperature = (java.lang.Integer)value$; break;
    case 2: stationId = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'year' field.
   */
  public java.lang.Integer getYear() {
    return year;
  }

  /**
   * Sets the value of the 'year' field.
   * @param value the value to set.
   */
  public void setYear(java.lang.Integer value) {
    this.year = value;
  }

  /**
   * Gets the value of the 'temperature' field.
   */
  public java.lang.Integer getTemperature() {
    return temperature;
  }

  /**
   * Sets the value of the 'temperature' field.
   * @param value the value to set.
   */
  public void setTemperature(java.lang.Integer value) {
    this.temperature = value;
  }

  /**
   * Gets the value of the 'stationId' field.
   */
  public java.lang.CharSequence getStationId() {
    return stationId;
  }

  /**
   * Sets the value of the 'stationId' field.
   * @param value the value to set.
   */
  public void setStationId(java.lang.CharSequence value) {
    this.stationId = value;
  }

  /** Creates a new record_field RecordBuilder */
  public static gwu.hadoop.avro.generated.record_field.Builder newBuilder() {
    return new gwu.hadoop.avro.generated.record_field.Builder();
  }
  
  /** Creates a new record_field RecordBuilder by copying an existing Builder */
  public static gwu.hadoop.avro.generated.record_field.Builder newBuilder(gwu.hadoop.avro.generated.record_field.Builder other) {
    return new gwu.hadoop.avro.generated.record_field.Builder(other);
  }
  
  /** Creates a new record_field RecordBuilder by copying an existing record_field instance */
  public static gwu.hadoop.avro.generated.record_field.Builder newBuilder(gwu.hadoop.avro.generated.record_field other) {
    return new gwu.hadoop.avro.generated.record_field.Builder(other);
  }
  
  /**
   * RecordBuilder for record_field instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<record_field>
    implements org.apache.avro.data.RecordBuilder<record_field> {

    private int year;
    private int temperature;
    private java.lang.CharSequence stationId;

    /** Creates a new Builder */
    private Builder() {
      super(gwu.hadoop.avro.generated.record_field.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(gwu.hadoop.avro.generated.record_field.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.year)) {
        this.year = data().deepCopy(fields()[0].schema(), other.year);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.temperature)) {
        this.temperature = data().deepCopy(fields()[1].schema(), other.temperature);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.stationId)) {
        this.stationId = data().deepCopy(fields()[2].schema(), other.stationId);
        fieldSetFlags()[2] = true;
      }
    }
    
    /** Creates a Builder by copying an existing record_field instance */
    private Builder(gwu.hadoop.avro.generated.record_field other) {
            super(gwu.hadoop.avro.generated.record_field.SCHEMA$);
      if (isValidValue(fields()[0], other.year)) {
        this.year = data().deepCopy(fields()[0].schema(), other.year);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.temperature)) {
        this.temperature = data().deepCopy(fields()[1].schema(), other.temperature);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.stationId)) {
        this.stationId = data().deepCopy(fields()[2].schema(), other.stationId);
        fieldSetFlags()[2] = true;
      }
    }

    /** Gets the value of the 'year' field */
    public java.lang.Integer getYear() {
      return year;
    }
    
    /** Sets the value of the 'year' field */
    public gwu.hadoop.avro.generated.record_field.Builder setYear(int value) {
      validate(fields()[0], value);
      this.year = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'year' field has been set */
    public boolean hasYear() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'year' field */
    public gwu.hadoop.avro.generated.record_field.Builder clearYear() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'temperature' field */
    public java.lang.Integer getTemperature() {
      return temperature;
    }
    
    /** Sets the value of the 'temperature' field */
    public gwu.hadoop.avro.generated.record_field.Builder setTemperature(int value) {
      validate(fields()[1], value);
      this.temperature = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'temperature' field has been set */
    public boolean hasTemperature() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'temperature' field */
    public gwu.hadoop.avro.generated.record_field.Builder clearTemperature() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'stationId' field */
    public java.lang.CharSequence getStationId() {
      return stationId;
    }
    
    /** Sets the value of the 'stationId' field */
    public gwu.hadoop.avro.generated.record_field.Builder setStationId(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.stationId = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'stationId' field has been set */
    public boolean hasStationId() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'stationId' field */
    public gwu.hadoop.avro.generated.record_field.Builder clearStationId() {
      stationId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    public record_field build() {
      try {
        record_field record = new record_field();
        record.year = fieldSetFlags()[0] ? this.year : (java.lang.Integer) defaultValue(fields()[0]);
        record.temperature = fieldSetFlags()[1] ? this.temperature : (java.lang.Integer) defaultValue(fields()[1]);
        record.stationId = fieldSetFlags()[2] ? this.stationId : (java.lang.CharSequence) defaultValue(fields()[2]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
