package com.comercio.cliente.builder;
import com.comercio.cliente.dto.ClienteDTO;

public class ClienteDTOBuilder {
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

        public ClienteDTO build() {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(this.id);
            clienteDTO.setNombre(this.nombre);
            clienteDTO.setTipoDocumento(this.tipoDocumento);
            clienteDTO.setNumeroDocumento(this.numeroDocumento);
            clienteDTO.setCorreo(this.correo);
            clienteDTO.setDireccion(this.direccion);
            clienteDTO.setActivo(this.activo);
            return clienteDTO;
        }
    }
}
