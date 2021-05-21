package ru.snx.lunchvotes.utils.exceptions;

public class ErrorInfo {
    private final String url;
    private final String[] errors;

    public ErrorInfo(CharSequence url, String[] errors) {
        this.url = url.toString();
        this.errors = errors;
    }

    public ErrorInfo(CharSequence url, String errors) {
        this.url = url.toString();
        this.errors = new String[]{errors};
    }

    public String getUrl() {
        return url;
    }

    public String[] getErrors() {
        return errors;
    }
}
