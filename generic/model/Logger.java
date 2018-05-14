package ba.telegroup.apps.gk.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by drstjepanovic on 8/18/2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Logger {
    private Integer id;
    private Timestamp created;
    private Integer userId;
    private String actionType;
    private String actionDetails;
    private String tableName;
    private Byte atomic;
    private Integer kompanijaId;

    public Logger() {}

    public Logger(Integer userId, String actionType, String actionDetails, String tableName, Byte atomic, Integer kompanijaId) {
        this.userId = userId;
        this.actionType = actionType;
        this.actionDetails = actionDetails;
        this.tableName = tableName;
        this.atomic = atomic;
        this.kompanijaId = kompanijaId;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "created", nullable = false, insertable = false, updatable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "action_type", nullable = false, length = 128)
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Basic
    @Column(name = "action_details", nullable = false, length = -1)
    public String getActionDetails() {
        return actionDetails;
    }

    public void setActionDetails(String actionDetails) {
        this.actionDetails = actionDetails;
    }

    @Basic
    @Column(name = "table_name", nullable = false, length = 128)
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "atomic", nullable = false)
    public Byte getAtomic() {
        return atomic;
    }

    public void setAtomic(Byte atomic) {
        this.atomic = atomic;
    }

    @Basic
    @Column(name = "kompanija_id", nullable = true)
    public Integer getKompanijaId() {
        return kompanijaId;
    }

    public void setKompanijaId(Integer kompanijaId) {
        this.kompanijaId = kompanijaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logger logger = (Logger) o;

        if (id != null ? !id.equals(logger.id) : logger.id != null) return false;
        if (created != null ? !created.equals(logger.created) : logger.created != null) return false;
        if (userId != null ? !userId.equals(logger.userId) : logger.userId != null) return false;
        if (actionType != null ? !actionType.equals(logger.actionType) : logger.actionType != null) return false;
        if (actionDetails != null ? !actionDetails.equals(logger.actionDetails) : logger.actionDetails != null)
            return false;
        if (tableName != null ? !tableName.equals(logger.tableName) : logger.tableName != null) return false;
        if (atomic != null ? !atomic.equals(logger.atomic) : logger.atomic != null) return false;
        return kompanijaId != null ? kompanijaId.equals(logger.kompanijaId) : logger.kompanijaId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        result = 31 * result + (actionDetails != null ? actionDetails.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (atomic != null ? atomic.hashCode() : 0);
        result = 31 * result + (kompanijaId != null ? kompanijaId.hashCode() : 0);
        return result;
    }

    public enum ActionType {
        CREATE("create"),
        UPDATE("update"),
        READ("read"),
        DELETE("delete");

        private final String text;

        private ActionType(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

}
