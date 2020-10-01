/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { VehiculeComponentsPage, VehiculeDeleteDialog, VehiculeUpdatePage } from './vehicule.page-object';

const expect = chai.expect;

describe('Vehicule e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let vehiculeUpdatePage: VehiculeUpdatePage;
    let vehiculeComponentsPage: VehiculeComponentsPage;
    let vehiculeDeleteDialog: VehiculeDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Vehicules', async () => {
        await navBarPage.goToEntity('vehicule');
        vehiculeComponentsPage = new VehiculeComponentsPage();
        await browser.wait(ec.visibilityOf(vehiculeComponentsPage.title), 5000);
        expect(await vehiculeComponentsPage.getTitle()).to.eq('jhipsterApp.vehicule.home.title');
    });

    it('should load create Vehicule page', async () => {
        await vehiculeComponentsPage.clickOnCreateButton();
        vehiculeUpdatePage = new VehiculeUpdatePage();
        expect(await vehiculeUpdatePage.getPageTitle()).to.eq('jhipsterApp.vehicule.home.createOrEditLabel');
        await vehiculeUpdatePage.cancel();
    });

    it('should create and save Vehicules', async () => {
        const nbButtonsBeforeCreate = await vehiculeComponentsPage.countDeleteButtons();

        await vehiculeComponentsPage.clickOnCreateButton();
        await promise.all([
            vehiculeUpdatePage.setIdentifiantInput('identifiant')
            // vehiculeUpdatePage.chauffeurSelectLastOption(),
        ]);
        expect(await vehiculeUpdatePage.getIdentifiantInput()).to.eq('identifiant');
        await vehiculeUpdatePage.save();
        expect(await vehiculeUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await vehiculeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Vehicule', async () => {
        const nbButtonsBeforeDelete = await vehiculeComponentsPage.countDeleteButtons();
        await vehiculeComponentsPage.clickOnLastDeleteButton();

        vehiculeDeleteDialog = new VehiculeDeleteDialog();
        expect(await vehiculeDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.vehicule.delete.question');
        await vehiculeDeleteDialog.clickOnConfirmButton();

        expect(await vehiculeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
