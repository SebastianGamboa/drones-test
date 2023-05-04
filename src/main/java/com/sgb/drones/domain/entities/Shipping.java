package com.sgb.drones.domain.entities;

import java.time.LocalDateTime;

import com.sgb.drones.domain.enums.ShippingStateEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shipping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShippingStateEnum state;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public Shipping(long id) {
        this.id = id;
    }

    public Shipping(Drone drone) {
        this.drone = drone;
    }

    public void initialState() {
        state = ShippingStateEnum.CREATED;
    }
}
