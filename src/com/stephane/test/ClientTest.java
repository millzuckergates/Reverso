package com.stephane.test;

import com.stephane.entity.Client;
import org.junit.jupiter.api.Assertions;
import com.stephane.exceptions.ReversoException;
import org.junit.Test;

public class ClientTest{
    @Test
    public void testSetVilleNull() {
        String ville = null;
        Client client = new Client();

        Assertions.assertThrows(ReversoException.class, () -> {
            client.setVille(ville);
        });
    }

    @Test
    public void testSetVilleValid() throws ReversoException {
        // Arrange
        Client client = new Client();
        String ville = "Paris";

        client.setVille(ville);

        Assertions.assertEquals(ville, client.getVille());
    }

    @Test
    public void testSetTelValid() throws ReversoException {
        // Arrange
        Client client = new Client();
        String tel = "0123456789";

        // Act
        client.setTel(tel);

        // Assert
        Assertions.assertEquals(tel, client.getTel());
    }

    @Test
    public void testSetTelInvalid() {
        // Arrange
        Client client = new Client();
        String tel = "012345678"; // Trop court

        // Act & Assert
        Assertions.assertThrows(ReversoException.class, () -> {
            client.setTel(tel);
        });
    }
}
