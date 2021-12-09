package com.github.ad.service;

import com.github.ad.request.CreativeRequest;
import com.github.ad.response.CreativeResponse;

public interface CreativeService {
    CreativeResponse createCreative(CreativeRequest request);
}
