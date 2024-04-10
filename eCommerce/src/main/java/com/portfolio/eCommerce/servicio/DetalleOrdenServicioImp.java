package com.portfolio.eCommerce.servicio;

import com.portfolio.eCommerce.modelo.DetalleOrden;
import com.portfolio.eCommerce.repositorio.DetalleOrdenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenServicioImp implements DetalleOrdenServicio{

    @Autowired
    private DetalleOrdenRepo detalleOrdenRepo;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepo.save(detalleOrden);
    }
}
