/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { Permis_de_conduitUpdateComponent } from 'app/entities/permis-de-conduit/permis-de-conduit-update.component';
import { Permis_de_conduitService } from 'app/entities/permis-de-conduit/permis-de-conduit.service';
import { Permis_de_conduit } from 'app/shared/model/permis-de-conduit.model';

describe('Component Tests', () => {
    describe('Permis_de_conduit Management Update Component', () => {
        let comp: Permis_de_conduitUpdateComponent;
        let fixture: ComponentFixture<Permis_de_conduitUpdateComponent>;
        let service: Permis_de_conduitService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [Permis_de_conduitUpdateComponent]
            })
                .overrideTemplate(Permis_de_conduitUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(Permis_de_conduitUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Permis_de_conduitService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Permis_de_conduit(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.permis_de_conduit = entity;
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
                    const entity = new Permis_de_conduit();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.permis_de_conduit = entity;
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
