package ba.unsa.etf.rpr.tutorijal02;

public class Interval {

    private double start = 0;
    private double end = 0;
    private boolean includesStart = false;
    private boolean includesEnd = false;

    public Interval(double start, double end, boolean includesStart, boolean includesEnd) {
        if (start > end) throw new IllegalArgumentException("Pogresan interval");

        this.start = start;
        this.end = end;
        this.includesStart = includesStart;
        this.includesEnd = includesEnd;
    }

    public Interval() {

    }

    public static Interval intersect(Interval i, Interval i2) {
        return i.intersect(i2);
    }

    public boolean isNull() {
        return start == 0 && end == 0 && !includesStart && !includesEnd;
    }

    public boolean isIn(double v) {
        return v > start && v < end || v == start && includesStart || v == end && includesEnd;
    }

    public Interval intersect(Interval interval) {
        double mStart = start, mEnd = end;
        boolean mIncludesStart = includesStart, mIncludesEnd = includesEnd;

        if (interval.start > mStart) {
            mStart = interval.start;
            mIncludesStart = interval.includesStart;
        } else if (interval.start == mStart && mIncludesStart)
            mIncludesStart = interval.includesStart;

        if (interval.end < mEnd) {
            mEnd = interval.end;
            mIncludesEnd = interval.includesEnd;
        } else if (interval.end == mEnd && mIncludesEnd)
            mIncludesEnd = interval.includesEnd;

        if (mStart > mEnd || mStart == mEnd && !mIncludesStart && !mIncludesEnd)
            return new Interval();

        return new Interval(mStart, mEnd, mIncludesStart, mIncludesEnd);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Interval && ((Interval) obj).start == start && ((Interval) obj).end == end
                && ((Interval) obj).includesStart == includesStart && ((Interval) obj).includesEnd == includesEnd;
    }

    @Override
    public String toString() {
        if (isNull()) return "()";
        return (includesStart ? "[" : "(") + start + "," + end + (includesEnd ? "]" : ")");
    }
}
