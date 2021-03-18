package netcracker.demo.dto;

import java.sql.Date;

public interface StudentDTO {
    int getId();
    String getName();
    Date getDateOfEnrollment();
    String getGroupName();
    Integer getGroupId();
    String getCity();
    Date getDateOfBirth();
    Integer getRoomNumber();
}
