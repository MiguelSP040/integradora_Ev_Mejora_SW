import { login } from './utils.js';

describe('Caso de prueba: Registro de secretaria', () => {
  it('Registro de secretaria', () => {
    login('miguel', 'miguel');
    cy.get('a[href="list_secretaries.html"]').should('be.visible').click();
    cy.get('button[data-bs-target="#registerModal"]').should('be.visible').click();

    cy.get('#registerModal').should('be.visible');
    cy.get('input[id="regNombres"]').should('be.visible').type("Ana");
    cy.get('input[id="regApellidoPaterno"]').should('be.visible').type("López");
    cy.get('input[id="regApellidoMaterno"]').should('be.visible').type("Gallardo");
    cy.get('input[id="regEmail"]').should('be.visible').type("ana@correo.com");
    cy.get('input[id="regTelefono"]').should('be.visible').type("7779045633");
    cy.get('input[id="regUsuario"]').should('be.visible').type("ana");
    cy.screenshot('CY08.1');
    cy.get('button[onclick="saveUser()"]').should('be.visible').click();

    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible');
    cy.screenshot('CY08.2');
    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();
    
    cy.wait(500).screenshot('CY08.3');
  });
});
