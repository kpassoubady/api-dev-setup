package com.apidev.quickstart.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record OrderRequest(@NotEmpty List<@Valid OrderLineItem> items) {
}
