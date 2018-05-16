package com.vitzro.dto;

public interface ISendMessage<I extends IMessage, O> extends IMessage {

	public O encode(I i);
}
