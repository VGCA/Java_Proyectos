package com.portfolio.ecommerce.servicio;

import com.portfolio.ecommerce.modelo.DetalleOrden;
import com.portfolio.ecommerce.repositorio.DetalleOrdenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenServicioImp implements DetalleOrdenServicio{

    private final DetalleOrdenRepo detalleOrdenRepo;

    public DetalleOrdenServicioImp(DetalleOrdenRepo detalleOrdenRepo) {
        this.detalleOrdenRepo = detalleOrdenRepo;
    }

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepo.save(detalleOrden);
    }
}
