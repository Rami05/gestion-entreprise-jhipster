import { element, by, ElementFinder } from 'protractor';

export class EntrepriseComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-entreprise div table .btn-danger'));
    title = element.all(by.css('jhi-entreprise div h2#page-heading span')).first();

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

export class EntrepriseUpdatePage {
    pageTitle = element(by.id('jhi-entreprise-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    raison_socialInput = element(by.id('field_raison_social'));
    chiffre_affaireInput = element(by.id('field_chiffre_affaire'));
    gerantInput = element(by.id('field_gerant'));
    secteurInput = element(by.id('field_secteur'));
    siegeInput = element(by.id('field_siege'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setRaison_socialInput(raison_social) {
        await this.raison_socialInput.sendKeys(raison_social);
    }

    async getRaison_socialInput() {
        return this.raison_socialInput.getAttribute('value');
    }

    async setChiffre_affaireInput(chiffre_affaire) {
        await this.chiffre_affaireInput.sendKeys(chiffre_affaire);
    }

    async getChiffre_affaireInput() {
        return this.chiffre_affaireInput.getAttribute('value');
    }

    async setGerantInput(gerant) {
        await this.gerantInput.sendKeys(gerant);
    }

    async getGerantInput() {
        return this.gerantInput.getAttribute('value');
    }

    async setSecteurInput(secteur) {
        await this.secteurInput.sendKeys(secteur);
    }

    async getSecteurInput() {
        return this.secteurInput.getAttribute('value');
    }

    async setSiegeInput(siege) {
        await this.siegeInput.sendKeys(siege);
    }

    async getSiegeInput() {
        return this.siegeInput.getAttribute('value');
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

export class EntrepriseDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-entreprise-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-entreprise'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
