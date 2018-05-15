package com.vitzro.protocol;

public interface IReceivedMessage<I> extends IMessage {

	public void decode(I i);
}
