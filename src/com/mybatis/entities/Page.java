package com.mybatis.entities;

import java.util.List;

/**
 * @author lijichen
 * @date 2020/12/9 - 16:36
 */
public class Page {
    private int start;
    private int end;
    private int count;
    private List<Employee> emps;

    public Page() {
    }

    public Page(int start, int end, int count, List<Employee> emps) {
        this.start = start;
        this.end = end;
        this.count = count;
        this.emps = emps;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", end=" + end +
                ", count=" + count +
                ", emps=" + emps +
                '}';
    }
}
