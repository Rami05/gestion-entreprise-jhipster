/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { Compte_bancaireDeleteDialogComponent } from 'app/entities/compte-bancaire/compte-bancaire-delete-dialog.component';
import { Compte_bancaireService } from 'app/entities/compte-bancaire/compte-bancaire.service';

describe('Component Tests', () => {
    describe('Compte_bancaire Management Delete Component', () => {
        let comp: Compte_bancaireDeleteDialogComponent;
        let fixture: ComponentFixture<Compte_bancaireDeleteDialogComponent>;
        let service: Compte_bancaireService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [Compte_bancaireDeleteDialogComponent]
            })
                .overrideTemplate(Compte_bancaireDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(Compte_bancaireDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Compte_bancaireService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
