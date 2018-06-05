package com.vitzro.service;

import com.vitzro.dto.IReceivedMessage;

public interface IReceivedMessageStroage extends IStroage{

	public void put(IReceivedMessage m);
}
