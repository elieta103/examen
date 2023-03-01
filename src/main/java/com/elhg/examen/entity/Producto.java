package com.elhg.examen.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(
        name="productos",
        schema="examen"
)
public class Producto {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "producto_generador"
    )
    @SequenceGenerator(
            name="producto_generador",
            sequenceName = "producto_sequence_name",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String nombre;


    private String descripcion;
    private BigDecimal precio;
    private boolean activo;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime ultimaActualizacion;

}
