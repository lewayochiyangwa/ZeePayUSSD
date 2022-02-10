package com.example.ZeePayUSSD.response;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlRootElement(
        name = "cps-message"
)
@XmlType(
        propOrder = {"sequence_number", "version", "service_type", "source_addr", "dest_addr", "timestamp", "command_status", "data_coding", "msg_len", "msg_content"}
)

public class UssdResponse {

    private String sequence_number;
    private String version;
    private String service_type;

    public UssdResponse(String sequence_number, String version, String service_type, String source_addr, String dest_addr, String timestamp, String command_status, String data_coding, String msg_len, String msg_content) {
        this.sequence_number = sequence_number;
        this.version = version;
        this.service_type = service_type;
        this.source_addr = source_addr;
        this.dest_addr = dest_addr;
        this.timestamp = timestamp;
        this.command_status = command_status;
        this.data_coding = data_coding;
        this.msg_len = msg_len;
        this.msg_content = msg_content;
    }

    public String getSequence_number() {
        return sequence_number;
    }

    public void setSequence_number(String sequence_number) {
        this.sequence_number = sequence_number;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    @Override
    public String toString() {
        return "UssdResponse{" +
                "sequence_number='" + sequence_number + '\'' +
                ", version='" + version + '\'' +
                ", service_type='" + service_type + '\'' +
                ", source_addr='" + source_addr + '\'' +
                ", dest_addr='" + dest_addr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", command_status='" + command_status + '\'' +
                ", data_coding='" + data_coding + '\'' +
                ", msg_len='" + msg_len + '\'' +
                ", msg_content='" + msg_content + '\'' +
                '}';
    }

    public String getSource_addr() {
        return source_addr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UssdResponse that = (UssdResponse) o;
        return Objects.equals(sequence_number, that.sequence_number) && Objects.equals(version, that.version) && Objects.equals(service_type, that.service_type) && Objects.equals(source_addr, that.source_addr) && Objects.equals(dest_addr, that.dest_addr) && Objects.equals(timestamp, that.timestamp) && Objects.equals(command_status, that.command_status) && Objects.equals(data_coding, that.data_coding) && Objects.equals(msg_len, that.msg_len) && Objects.equals(msg_content, that.msg_content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequence_number, version, service_type, source_addr, dest_addr, timestamp, command_status, data_coding, msg_len, msg_content);
    }

    public void setSource_addr(String source_addr) {
        this.source_addr = source_addr;
    }

    public String getDest_addr() {
        return dest_addr;
    }

    public void setDest_addr(String dest_addr) {
        this.dest_addr = dest_addr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCommand_status() {
        return command_status;
    }

    public void setCommand_status(String command_status) {
        this.command_status = command_status;
    }

    public String getData_coding() {
        return data_coding;
    }

    public void setData_coding(String data_coding) {
        this.data_coding = data_coding;
    }

    public String getMsg_len() {
        return msg_len;
    }

    public void setMsg_len(String msg_len) {
        this.msg_len = msg_len;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    private String source_addr;

    public UssdResponse() {
    }

    private String dest_addr;
    private String timestamp;
    private String command_status;
    private String data_coding;
    private String msg_len;
    private String msg_content;
}
