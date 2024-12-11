package org.example;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastDistributed {
    public static void main(String[] args) {
        Config config = new Config();

        // Configurar nombre del clúster
        config.setClusterName("hazelcast-cluster");

        // Configurar TCP-IP y desactivar Multicast
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig()
                .setEnabled(true)
                .addMember("hazelcast-node1")
                .addMember("hazelcast-node2");
        config.getNetworkConfig().getInterfaces()
                .setEnabled(true)
                .addInterface("192.168.1.*");
        config.getNetworkConfig().getJoin().getTcpIpConfig()
                .setEnabled(true)
                .addMember("192.168.1.33") // IP del nodo 1
                .addMember("192.168.1.44"); // IP del nodo 2

        // Iniciar instancia Hazelcast
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        var map = hazelcastInstance.getMap("example-map");

        // Añadir datos al mapa
        map.put("luna", "entre cortina y cortina");
        System.out.println("Value for carlos: " + map.get("carlos"));
        System.out.println("Value for luna: " + map.get("luna"));
    }
}
