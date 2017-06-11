package org.centauri.cloud.cloud.network;

import lombok.Getter;
import org.centauri.cloud.cloud.network.packets.Packet;
import org.centauri.cloud.cloud.network.packets.PacketServerRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacketManager {

	@Getter private static PacketManager instance = new PacketManager();
	@Getter private List<Class<? extends Packet>> packets = new ArrayList<>(Arrays.asList(
			PacketServerRegister.class
	));

	public int register(Class<? extends Packet> packetClass) {
		packets.add(packetClass);
		return getId(packetClass);
	}

	public byte getId(Class<? extends Packet> packetClass) {
		return (byte) packets.indexOf(packetClass);
	}

}
