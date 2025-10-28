package com.empresa.crudmixto.entity;

public class Tarea {
    private String descripcion;
    private String estado; // "Sin iniciar", "En proceso", "Completado"

    public Tarea() {
        this.estado = "Sin iniciar";
    }

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.estado = "Sin iniciar";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCompletada(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCompletada'");
    }

    
}