/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { Compte_bancaireUpdateComponent } from 'app/entities/compte-bancaire/compte-bancaire-update.component';
import { Compte_bancaireService } from 'app/entities/compte-bancaire/compte-bancaire.service';
import { Compte_bancaire } from 'app/shared/model/compte-bancaire.model';

describe('Component Tests', () => {
    describe('Compte_bancaire Management Update Component', () => {
        let comp: Compte_bancaireUpdateComponent;
        let fixture: ComponentFixture<Compte_bancaireUpdateComponent>;
        let service: Compte_bancaireService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [Compte_bancaireUpdateComponent]
            })
                .overrideTemplate(Compte_bancaireUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(Compte_bancaireUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Compte_bancaireService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Compte_bancaire(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.compte_bancaire = entity;
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
                    const entity = new Compte_bancaire();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.compte_bancaire = entity;
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
