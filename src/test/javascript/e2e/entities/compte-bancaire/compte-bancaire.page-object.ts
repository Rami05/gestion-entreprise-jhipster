import { element, by, ElementFinder } from 'protractor';

export class Compte_bancaireComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-compte-bancaire div table .btn-danger'));
    title = element.all(by.css('jhi-compte-bancaire div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class Compte_bancaireUpdatePage {
    pageTitle = element(by.id('jhi-compte-bancaire-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    ribInput = element(by.id('field_rib'));
    agenceInput = element(by.id('field_agence'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setRibInput(rib) {
        await this.ribInput.sendKeys(rib);
    }

    async getRibInput() {
        return this.ribInput.getAttribute('value');
    }

    async setAgenceInput(agence) {
        await this.agenceInput.sendKeys(agence);
    }

    async getAgenceInput() {
        return this.agenceInput.getAttribute('value');
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class Compte_bancaireDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-compte_bancaire-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-compte_bancaire'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
