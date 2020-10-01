import { element, by, ElementFinder } from 'protractor';

export class VehiculeComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-vehicule div table .btn-danger'));
    title = element.all(by.css('jhi-vehicule div h2#page-heading span')).first();

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

export class VehiculeUpdatePage {
    pageTitle = element(by.id('jhi-vehicule-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    identifiantInput = element(by.id('field_identifiant'));
    chauffeurSelect = element(by.id('field_chauffeur'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setIdentifiantInput(identifiant) {
        await this.identifiantInput.sendKeys(identifiant);
    }

    async getIdentifiantInput() {
        return this.identifiantInput.getAttribute('value');
    }

    async chauffeurSelectLastOption() {
        await this.chauffeurSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async chauffeurSelectOption(option) {
        await this.chauffeurSelect.sendKeys(option);
    }

    getChauffeurSelect(): ElementFinder {
        return this.chauffeurSelect;
    }

    async getChauffeurSelectedOption() {
        return this.chauffeurSelect.element(by.css('option:checked')).getText();
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

export class VehiculeDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-vehicule-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-vehicule'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
