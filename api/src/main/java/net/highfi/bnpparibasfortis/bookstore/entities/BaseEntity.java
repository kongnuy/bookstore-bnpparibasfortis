package net.highfi.bnpparibasfortis.bookstore.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import net.highfi.bnpparibasfortis.bookstore.utils.DateUtils;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  protected Long id;

  @Column(name = "UUID", unique = true, nullable = false)
  protected String uuid;

  @Column(name = "CREATED_AT", nullable = false)
  protected Timestamp createdAt;

  @Column(name = "UPDATED_AT")
  protected Timestamp updatedAt;

  @Column(name = "ROW_INDEX", columnDefinition = "text")
  protected String rowIndex;

  protected void buildRowIndex() {
    if (id != null) {
      this.appendToRowIndex(String.valueOf(id));
    }
    this.appendToRowIndex(uuid);
    this.appendToRowIndex(DateUtils.format(createdAt));
    this.appendToRowIndex(DateUtils.format(updatedAt));
  }

  protected void setDefaults() {
    if (id == null) {
      this.setUuid(UUID.randomUUID().toString());
      this.setCreatedAt(Timestamp.from(Instant.now()));
    } else {
      this.setUpdatedAt(Timestamp.from(Instant.now()));
    }
  }

  protected void appendToRowIndex(String field) {
    if (field != null && !field.isEmpty()) {
      if (this.rowIndex != null && !this.rowIndex.isEmpty()) {
        this.rowIndex += "|";
      } else if (this.rowIndex == null) {
        this.rowIndex = "";
      }
      this.rowIndex += field;
    }
  }

}
