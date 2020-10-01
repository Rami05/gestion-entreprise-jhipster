import { element, by, ElementFinder } from 'protractor';

export class ContratComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-contrat div table .btn-danger'));
    title = element.all(by.css('jhi-contrat div h2#page-heading span')).first();

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

export class ContratUpdatePage {
    pageTitle = element(by.id('jhi-contrat-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    referenceInput = element(by.id('field_reference'));
    employeSelect = element(by.id('field_employe'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setReferenceInput(reference) {
        await this.referenceInput.sendKeys(reference);
    }

    async getReferenceInput() {
        return this.referenceInput.getAttribute('value');
    }

    async employeSelectLastOption() {
        await this.employeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async employeSelectOption(option) {
        await this.employeSelect.sendKeys(option);
    }

    getEmployeSelect(): ElementFinder {
        return this.employeSelect;
    }

    async getEmployeSelectedOption() {
        return this.employeSelect.element(by.css('option:checked')).getText();
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

export class ContratDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-contrat-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-contrat'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
