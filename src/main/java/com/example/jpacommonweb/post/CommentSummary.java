package com.example.jpacommonweb.post;

import org.springframework.beans.factory.annotation.Value;

public interface CommentSummary {
    String getComment();

    int getUp();

    int getDown();

    default  String getVotes() {
        return getUp()+ " " + getDown();
    }

    //open projecttion ==> why ?? target이 한정되지 않아서
//    @Value("#{target.up+ ' ' + target.down}")
//    String getVotes();
}
