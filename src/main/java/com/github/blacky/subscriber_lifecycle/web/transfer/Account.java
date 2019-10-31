package com.github.blacky.subscriber_lifecycle.web.transfer;

import com.github.blacky.subscriber_lifecycle.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private long balance;
    private Status status;
}
