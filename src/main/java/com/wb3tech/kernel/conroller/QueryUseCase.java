package com.wb3tech.kernel.conroller;

import com.wb3tech.kernel.boundary.Request;
import com.wb3tech.kernel.boundary.Response;

public interface QueryUseCase<Req extends Request, Resp extends Response> {
    Resp execute(Req request);
}
