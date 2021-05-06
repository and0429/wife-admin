import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/4/27
 * @className CommonTest
 */
public class CommonTest {

    /**
     * @param start
     * @return
     */
    public List<String> dateRange(String start, String end) throws ParseException {
        final List<String> list = new ArrayList<>();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        list.add(start);
        final long _start = format.parse(start).getTime();
        final long _end = format.parse(end).getTime();
        final long interval = 1000L * 60 * 60 * 24 * 32;
        for (long i = _start + interval; i < _end; i += interval) {
            final String yearmonth = format.format(new Date(i));
            list.add(yearmonth);
        }
        list.add(end);
        return list;
    }


    @Test
    public void test() throws ParseException {
        final List<String> list = ff("2020-02", "2040-02");
        System.out.println(list.size());
        list.forEach(System.out::println);
    }


    public List<String> ff(String start, String end) throws ParseException {
        final List<String> list = new ArrayList<>();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        list.add(start);
        final long _start = format.parse(start).getTime();
        final long _end = format.parse(end).getTime();
        final long interval = 1000L * 60 * 60 * 24 * 32;
        for (long i = _start + interval; i < _end; ) {
            final String yearmonth = format.format(new Date(i));
            final Date parse = format.parse(yearmonth);
            i = parse.getTime() + interval;
            list.add(yearmonth);
        }
        list.add(end);
        return list;
    }


}
