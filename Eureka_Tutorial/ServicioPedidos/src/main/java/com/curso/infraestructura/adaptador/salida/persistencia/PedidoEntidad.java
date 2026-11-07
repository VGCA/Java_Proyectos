package com.curso.infraestructura.adaptador.salida.persistencia;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PEDIDO")
@NamedEntityGraph(
        name = "PedidoConDetalles",
        attributeNodes = {
                @NamedAttributeNode("detalles")
        }
)
public class PedidoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1, max = 10)
    private String codigo;

    private String fecha;

    @NotEmpty
    private String estado;

    @ManyToOne
    @JoinColumn(name = "fk_login_cliente")
    private ClienteEntidad cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedidoEntidad> detalles = new ArrayList<>();

    public PedidoEntidad() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ClienteEntidad getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntidad cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedidoEntidad> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoEntidad> detalles) {
        this.detalles = detalles;
    }

}
