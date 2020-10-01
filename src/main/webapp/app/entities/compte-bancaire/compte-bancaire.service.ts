import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICompte_bancaire } from 'app/shared/model/compte-bancaire.model';

type EntityResponseType = HttpResponse<ICompte_bancaire>;
type EntityArrayResponseType = HttpResponse<ICompte_bancaire[]>;

@Injectable({ providedIn: 'root' })
export class Compte_bancaireService {
    public resourceUrl = SERVER_API_URL + 'api/compte-bancaires';

    constructor(protected http: HttpClient) {}

    create(compte_bancaire: ICompte_bancaire): Observable<EntityResponseType> {
        return this.http.post<ICompte_bancaire>(this.resourceUrl, compte_bancaire, { observe: 'response' });
    }

    update(compte_bancaire: ICompte_bancaire): Observable<EntityResponseType> {
        return this.http.put<ICompte_bancaire>(this.resourceUrl, compte_bancaire, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICompte_bancaire>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICompte_bancaire[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
