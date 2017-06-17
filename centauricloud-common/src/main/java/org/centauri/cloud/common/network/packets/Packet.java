package org.centauri.cloud.common.network.packets;

import io.netty.buffer.ByteBuf;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.UUID;

public interface Packet {
	
	void encode(ByteBuf buf);
	
	void decode(ByteBuf buf);

	default String readString(ByteBuf buf) {
		int length = buf.readInt();
		byte[] data = new byte[length];
		buf.readBytes(data);
		try{
			return new String(data, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	default void writeString(String msg, ByteBuf buf) {
		byte[] data = msg.getBytes(Charset.forName("UTF-8"));
		buf.writeInt(data.length);
		buf.writeBytes(data);
	}
	
	default byte[] readBytes(ByteBuf buf) {
		int length = buf.readInt();
		byte[] data = new byte[length];
		buf.readBytes(data);
		return data;
	}

	default UUID readUUID(ByteBuf buf) {
		long firstBits = buf.readLong();
		long lastBits = buf.readLong();
		return new UUID(firstBits, lastBits);
	}

	default void writeUUID(UUID uuid, ByteBuf buf) {
		buf.writeLong(uuid.getMostSignificantBits());
		buf.writeLong(uuid.getLeastSignificantBits());
	}
	
	default void writeBytes(byte[] data, ByteBuf buf) {
		buf.writeInt(data.length);
		buf.writeBytes(data);
	}
	
}
