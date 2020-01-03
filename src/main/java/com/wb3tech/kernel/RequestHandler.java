package com.wb3tech.kernel;

public interface RequestHandler<Request> {
    void Handle(Request request);
}
