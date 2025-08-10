package com.farukgenc.boilerplate.springboot.model.ids;

import com.farukgenc.boilerplate.springboot.model.Activity;
import com.farukgenc.boilerplate.springboot.model.User;

import java.io.Serial;
import java.io.Serializable;


public class ActivityParticipantId implements Serializable {
    @Serial
    private static final long serialVersionUID = -5861180923335350323L;

    private User user;
    private Activity activity;
}
