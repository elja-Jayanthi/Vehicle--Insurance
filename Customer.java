package com.auto.insurance;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    Long id;
    String name;
    int age;
    String address;

    String ssn;
    private String EMail;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public String getSsn() {
            return ssn;
        }
        public void setSsn(String ssn) {
            this.ssn = ssn;
        }


    @OneToMany(mappedBy = "owner")
    List<Vehicle> vehicles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @PrePersist
    public void beforeSave() throws Exception {

        setStatus("PENDING");


        /** local call **/
//        CreditRepository creditRepository = Application.getApplicationContext().getBean(CreditRepository.class);
//
//        Optional<Credit> creditOptional = creditRepository.findById(getSsn());
//
//        Credit credit = creditOptional.get();



        /** remote call **/

        CreditService creditService = Application.getApplicationContext().getBean(CreditService.class);
        Credit credit = creditService.getCredit(getSsn());

        System.out.println("Credit response from  :" +  credit.getRatingAgent());

        if(credit.getCreditRate().compareTo("B") > 0 ){
            throw new Exception("신용도가 B 이상이어야 합니다. (rated by " + credit.getRatingAgent()+")");
        }

    }

//    @PostPersist
    public void afterSave() throws Exception {

        //이메일 발송
        System.out.println(getName() + "님, 가입을 축하합니다");

        /** event pub-sub 방식 **/

        Streams streams = Application.getApplicationContext().getBean(Streams.class);

        MessageChannel messageChannel = streams.outboundChannel();

        CustomerRegisteredEvent customerRegisteredEvent = new CustomerRegisteredEvent();
        customerRegisteredEvent.setCouponNumber("coupon for " + getName());
        customerRegisteredEvent.setCustomerName(getName());
        customerRegisteredEvent.setEmailAddress(getEMail());
        customerRegisteredEvent.setSsn(getSsn());

        messageChannel.send(MessageBuilder
                .withPayload(customerRegisteredEvent)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());

        System.out.println("Event published");

    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }
}

