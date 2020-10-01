/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { VehiculeComponent } from 'app/entities/vehicule/vehicule.component';
import { VehiculeService } from 'app/entities/vehicule/vehicule.service';
import { Vehicule } from 'app/shared/model/vehicule.model';

describe('Component Tests', () => {
    describe('Vehicule Management Component', () => {
        let comp: VehiculeComponent;
        let fixture: ComponentFixture<VehiculeComponent>;
        let service: VehiculeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [VehiculeComponent],
                providers: []
            })
                .overrideTemplate(VehiculeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VehiculeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VehiculeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Vehicule(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.vehicules[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
