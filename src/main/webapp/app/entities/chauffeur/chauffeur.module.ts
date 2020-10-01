import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterSharedModule } from 'app/shared';
import {
    ChauffeurComponent,
    ChauffeurDetailComponent,
    ChauffeurUpdateComponent,
    ChauffeurDeletePopupComponent,
    ChauffeurDeleteDialogComponent,
    chauffeurRoute,
    chauffeurPopupRoute
} from './';

const ENTITY_STATES = [...chauffeurRoute, ...chauffeurPopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ChauffeurComponent,
        ChauffeurDetailComponent,
        ChauffeurUpdateComponent,
        ChauffeurDeleteDialogComponent,
        ChauffeurDeletePopupComponent
    ],
    entryComponents: [ChauffeurComponent, ChauffeurUpdateComponent, ChauffeurDeleteDialogComponent, ChauffeurDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterChauffeurModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
