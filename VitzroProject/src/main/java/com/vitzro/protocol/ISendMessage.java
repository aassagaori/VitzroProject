package com.vitzro.protocol;

public interface ISendMessage<I extends IMessage, O> extends IMessage {

	public O encode(I i);
}
