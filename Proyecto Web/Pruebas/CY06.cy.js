import { login } from './utils.js';

describe('Caso de prueba: Cambio de contraseña', () => {
  it('Cambio de contraseña', () => {
    login('rubi', 'rubi');
    cy.get('a[href="secretary_profile.html"]').should('be.visible').click();
    cy.get('button[id="cambiar-tab"]').should('be.visible').click();

    cy.get('input[id="currentPassword"]').should('be.visible').type("rubi");
    cy.get('input[id="newPassword"]').should('be.visible').type("rubi123");
    cy.get('input[id="confirmPassword"]').should('be.visible').type("rubi123");
    cy.get('button[id="changePasswordButton"]').should('not.be.disabled').click();
    cy.screenshot('CY06.1');

    cy.get('button[class="swal2-confirm swal2-styled swal2-default-outline"]').should('be.visible').click();
    cy.wait(500).screenshot('CY06.2');

    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();
  });
});
