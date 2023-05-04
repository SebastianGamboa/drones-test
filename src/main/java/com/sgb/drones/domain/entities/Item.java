package com.sgb.drones.domain.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

import com.sgb.drones.domain.constants.MessageConst;
import com.sgb.drones.domain.exceptions.ItemException;
import com.sgb.drones.web.constants.Regexp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int weight;

    @Column(nullable = false)
    private String code;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id")
    private ItemType itemType;

    public Item(long id) {
        this.id = id;
    }

    public void validate() {
        validateName();
        validateCode();
        validateImageUrl();
    }

    private void validateName() {
        Pattern pattern = Pattern.compile(Regexp.ALPHANUMERIC);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            throw new ItemException(
                HttpStatus.BAD_REQUEST,
                String.format(MessageConst.INVALID_ITEM_NAME, name)
            );
        }
    }

    private void validateCode() {
        Pattern pattern = Pattern.compile(Regexp.UPPERCASE_ALPHANUMERIC);
        Matcher matcher = pattern.matcher(code);
        if (!matcher.matches()) {
            throw new ItemException(
                HttpStatus.BAD_REQUEST,
                String.format(MessageConst.INVALID_ITEM_CODE, code)
            );
        }
    }

    private void validateImageUrl() {
        Pattern pattern = Pattern.compile(Regexp.URL);
        Matcher matcher = pattern.matcher(imageUrl);
        if (!matcher.matches()) {
            throw new ItemException(
                HttpStatus.BAD_REQUEST,
                String.format(MessageConst.INVALID_ITEM_IMAGE_URL, imageUrl)
            );
        }
    }
}
