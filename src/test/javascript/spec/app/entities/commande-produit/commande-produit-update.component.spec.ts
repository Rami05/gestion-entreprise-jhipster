/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { COMMANDE_PRODUITUpdateComponent } from 'app/entities/commande-produit/commande-produit-update.component';
import { COMMANDE_PRODUITService } from 'app/entities/commande-produit/commande-produit.service';
import { COMMANDE_PRODUIT } from 'app/shared/model/commande-produit.model';

describe('Component Tests', () => {
    describe('COMMANDE_PRODUIT Management Update Component', () => {
        let comp: COMMANDE_PRODUITUpdateComponent;
        let fixture: ComponentFixture<COMMANDE_PRODUITUpdateComponent>;
        let service: COMMANDE_PRODUITService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [COMMANDE_PRODUITUpdateComponent]
            })
                .overrideTemplate(COMMANDE_PRODUITUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(COMMANDE_PRODUITUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(COMMANDE_PRODUITService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new COMMANDE_PRODUIT(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.cOMMANDE_PRODUIT = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new COMMANDE_PRODUIT();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.cOMMANDE_PRODUIT = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
