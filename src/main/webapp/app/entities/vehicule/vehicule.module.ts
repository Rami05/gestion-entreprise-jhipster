import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterSharedModule } from 'app/shared';
import {
    VehiculeComponent,
    VehiculeDetailComponent,
    VehiculeUpdateComponent,
    VehiculeDeletePopupComponent,
    VehiculeDeleteDialogComponent,
    vehiculeRoute,
    vehiculePopupRoute
} from './';

const ENTITY_STATES = [...vehiculeRoute, ...vehiculePopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VehiculeComponent,
        VehiculeDetailComponent,
        VehiculeUpdateComponent,
        VehiculeDeleteDialogComponent,
        VehiculeDeletePopupComponent
    ],
    entryComponents: [VehiculeComponent, VehiculeUpdateComponent, VehiculeDeleteDialogComponent, VehiculeDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterVehiculeModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
