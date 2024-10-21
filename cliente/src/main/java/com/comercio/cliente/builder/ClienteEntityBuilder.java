package com.comercio.cliente.builder;

import com.comercio.cliente.model.ClienteEntity;

public class ClienteEntityBuilder {
    public static class Builder {
        private Long id;
        private String nombre;
        private String tipoDocumento;
        private String numeroDocumento;
        private String correo;
        private String direccion;
        private Boolean activo;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder withTipoDocumento(String tipoDocumento) {
            this.tipoDocumento = tipoDocumento;
            return this;
        }

        public Builder withNumeroDocumento(String numeroDocumento) {
            this.numeroDocumento = numeroDocumento;
            return this;
        }

        public Builder withCorreo(String correo) {
            this.correo = correo;
            return this;
        }

        public Builder withDireccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder withActivo(Boolean activo) {
            this.activo = activo;
            return this;
        }

        public ClienteEntity build() {
            ClienteEntity clienteEntity = new ClienteEntity();
            clienteEntity.setId(this.id);
            clienteEntity.setNombre(this.nombre);
            clienteEntity.setTipoDocumento(this.tipoDocumento);
            clienteEntity.setNumeroDocumento(this.numeroDocumento);
            clienteEntity.setCorreo(this.correo);
            clienteEntity.setDireccion(this.direccion);
            clienteEntity.setActivo(this.activo);
            return clienteEntity;
        }
    }
}
