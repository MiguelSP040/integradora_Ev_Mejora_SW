import { login } from './utils.js';

describe('Caso de prueba: Registro de enfermera', () => {
  it('Registro de enfermera', () => {
    login('miguel', 'miguel');
    cy.get('a[href="list_nurses.html"]').should('be.visible').click();
    cy.get('button[data-bs-target="#registerModal"]').should('be.visible').click();

    cy.get('#registerModal').should('be.visible');
    cy.get('input[id="regNombres"]').should('be.visible').type("Alana");
    cy.get('input[id="regApellidoPaterno"]').should('be.visible').type("Najera");
    cy.get('input[id="regApellidoMaterno"]').should('be.visible').type("Centeno");
    cy.get('input[id="regEmail"]').should('be.visible').type("alana@correo.com");
    cy.get('input[id="regTelefono"]').should('be.visible').type("7779824254");
    cy.get('select[id="regFloor"]').select('Piso 01');
    cy.get('input[id="regUsuario"]').should('be.visible').type("alana");
    cy.screenshot('CY07.1');
    cy.get('button[onclick="saveUser()"]').should('be.visible').click();

    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible');
    cy.screenshot('CY07.2');
    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();
    
    cy.wait(500).screenshot('CY07.3');
  });
});
