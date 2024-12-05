package org.example;

// Clase principal para ejecutar tareas distribuidas usando Hazelcast

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.cluster.Member;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class HazelcastDistributedTest {

    public static void main(String[] args) {
        // Crear o unirse a una instancia del clúster Hazelcast
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        // Obtener el servicio de ejecución distribuido
        IExecutorService executorService = hazelcastInstance.getExecutorService("test-executor");

        // Imprimir información sobre los miembros del clúster
        System.out.println("Miembros del clúster:");
        for (Member member : hazelcastInstance.getCluster().getMembers()) {
            System.out.println(" - " + member.getAddress());
        }

        // Enviar tareas al clúster
        System.out.println("Ejecutando tareas distribuidas...");
        executorService.executeOnAllMembers(new TestTask());

        // Agregar un delay para permitir la ejecución antes de detener el nodo
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Apagar la instancia local
        hazelcastInstance.shutdown();
    }

    // Tarea distribuida que implementa Serializable para que Hazelcast pueda enviarla a otros miembros
    public static class TestTask implements Runnable, Serializable {

        @Override
        public void run() {
            System.out.println("Tarea ejecutada en nodo: " + System.getProperty("user.name")
                    + ", IP: " + Hazelcast.getHazelcastInstanceByName("dev").getCluster().getLocalMember().getAddress());
        }
    }
}

