package gwu.impl;

/**
 * Created by wgtmac on 9/25/16.
 *
 * Example code to demonstrate lazy initialization in Java
 * Use case: very expensive to create one but is occasionally used
 */
public class LazyInitialization {

    /**
     * Very bad implementation
     */
    synchronized FieldType getField() {
        if (field == null)
            field = computeFieldValue();
        return field;
    }

    private static class FieldType {};
    private FieldType computeFieldValue() { return new FieldType(); }

    /**
     * 1. Double-check idiom for lazy initialization of instance fields
     *
     * Because there is no locking if the field is already initialized, it is
     * critical that the field be declared volatile
     */
    private volatile FieldType field;
    FieldType getField1() {
        FieldType result = field;
        if (result == null) {         // First check (no locking)
            synchronized(this) {
                result = field;
                if (result == null)   // Second check (with locking)
                    field = result = computeFieldValue();
            }
        }
        return result;
    }

    /**
     * 2. Lazy initialization holder class idiom for static fields
     *
     * It should be a static field. It has very few cost (no synchronization
     * after initialized)
     */
    private static class FieldHolder {
        static final FieldType field = FieldHolder.computeFieldValue();
        private static FieldType computeFieldValue() { return new FieldType(); }
    }
    static FieldType getField2() { return FieldHolder.field; }

}
